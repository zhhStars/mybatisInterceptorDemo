CREATE TABLE `role`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '����ʱ��',
  `creator_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '������',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '��ɫ����',
  `is_deleted` int(11) NULL DEFAULT NULL COMMENT 'ɾ����ǣ�0��ʾ���ڣ�1��ʾɾ��',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '��ɫ����',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '����ʱ��',
  `update_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '������',
  `is_administrators` int(11) NULL DEFAULT NULL COMMENT '�Ƿ񳬼�����Ա��1��ʾ�ǣ�������ʾ����',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;