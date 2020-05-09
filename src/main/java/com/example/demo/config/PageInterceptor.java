package com.example.demo.config;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import com.example.demo.util.ReflectHelper;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class, Integer.class})})
public class PageInterceptor implements Interceptor{
	
	 private static final Logger log = LoggerFactory.getLogger(PageInterceptor.class);
	
	 private static String pageSqlId = "";

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		log.info("进入 PageInterceptor 拦截器...");
		if(invocation.getTarget() instanceof RoutingStatementHandler){
			RoutingStatementHandler statementHandler = (RoutingStatementHandler) invocation.getTarget();
			BaseStatementHandler delegate = (BaseStatementHandler) ReflectHelper.getFieldValue(statementHandler, "delegate");
			MappedStatement mappedStatement = (MappedStatement)ReflectHelper.getFieldValue(delegate, "mappedStatement");
			// 拦截以指定后缀结尾的id 修改sql语句实现分页
			if(mappedStatement.getId().matches(pageSqlId)){
				BoundSql boundSql = delegate.getBoundSql();
				Object parameterObject = boundSql.getParameterObject();
				// 参数为Page对象
				if(parameterObject instanceof Page){
					updateSql(parameterObject, boundSql, mappedStatement, invocation);
				}
			}
		}
		return invocation.proceed();
	}
	
	@Override
	public Object plugin(Object target) { 
		return Plugin.wrap(target, this);
	}

	// setProperties方法可以在项目启动时获取配置文件中<property> 标签的指定值
	@Override
	public void setProperties(Properties properties){
		pageSqlId =  properties.getProperty("pageSqlId");
	}


	private void updateSql(Object param, BoundSql boundSql, MappedStatement mappedStatement, Invocation invocation) {
		try {
			Page page = (Page) param;
			if(page != null){
				String sql = boundSql.getSql();
				String databaseId = mappedStatement.getDatabaseId();
				// 获取相关配置
				Configuration config = mappedStatement.getConfiguration();
				Connection connection = config.getEnvironment().getDataSource().getConnection();
				// 拼接查询当前条件的sql的总条数
				String countSql = "SELECT COUNT(0) FROM (" + sql + ")  TMP_COUNT";
				PreparedStatement preparedStatement = connection.prepareStatement(countSql);
				BoundSql countBoundSql = new BoundSql(config, countSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
				ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, param, countBoundSql);
				parameterHandler.setParameters(preparedStatement);
				//执行获得总条数
				ResultSet rs = preparedStatement.executeQuery();
				int count = 0;
				if (rs.next()) {
					count = rs.getInt(1);
				}
				page.setTotalNumber(count);
				StringBuffer pageSql = new StringBuffer();
				switch (databaseId){
					case "oracle":
						pageSql.append("SELECT * FROM (SELECT TMP_TB.*,ROWNUM ROW_ID FROM (");
						pageSql.append(sql);
						pageSql.append(") TMP_TB WHERE ROWNUM<=");
						pageSql.append(page.getStart() + page.getPageSize());
						pageSql.append(") WHERE ROW_ID>");
						pageSql.append(page.getStart());
						break;
					case "mysql":
						pageSql.append(sql);
						pageSql.append(" LIMIT " + page.getStart() + "," + page.getPageSize());
						break;
					case "postgres":
						pageSql.append(sql);
						pageSql.append(" LIMIT " + page.getPageSize() + " OFFSET " + page.getStart());
						break;
					default:
				}
				// 反射修改sql语句
				Field field = boundSql.getClass().getDeclaredField("sql");
				field.setAccessible(true);
				field.set(boundSql, pageSql.toString());
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
