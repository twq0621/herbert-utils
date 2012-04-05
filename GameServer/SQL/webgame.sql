/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2012-4-5 15:50:22                            */
/*==============================================================*/

DROP TABLE IF EXISTS TBL_BASE_BAG;

DROP TABLE IF EXISTS TBL_BASE_ITEM;

DROP TABLE IF EXISTS TBL_BASE_SKILL;

DROP TABLE IF EXISTS TBL_ROLE;

DROP TABLE IF EXISTS TBL_ROLE_FRIEND;

DROP TABLE IF EXISTS TBL_ROLE_SHORTCUT;

DROP TABLE IF EXISTS TBL_USER;

/*==============================================================*/
/* Table: TBL_BASE_BAG                                          */
/*==============================================================*/
CREATE TABLE TBL_BASE_BAG
(
   ID                   BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
   BAG_TYPE             TINYINT NOT NULL COMMENT '背包类型',
   ROLE_ID              BIGINT NOT NULL COMMENT '角色ID',
   MAX                  TINYINT COMMENT '背包最大容量',
   ITEMS                VARCHAR(500) COMMENT '背包所有物品ID以逗号相隔',
   PRIMARY KEY (ID)
);

ALTER TABLE TBL_BASE_BAG COMMENT '背包基本属性';

/*==============================================================*/
/* Table: TBL_BASE_ITEM                                         */
/*==============================================================*/
CREATE TABLE TBL_BASE_ITEM
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT 'ID',
   TYPE                 VARCHAR(50) NOT NULL COMMENT '类型',
   NAME                 VARCHAR(150) NOT NULL COMMENT '物品名称',
   LEVEL                TINYINT COMMENT '使用最低级别',
   ICON                 INT COMMENT '图标',
   QUALITY              TINYINT COMMENT '品质',
   PROFESSION           TINYINT COMMENT '职业限制',
   VALID_TIME           DATETIME COMMENT '有效期',
   PRICE                INT COMMENT '价格',
   COLOR_ID             INT COMMENT '颜色ID',
   COUNT                INT COMMENT '物品总数',
   MAX                  TINYINT COMMENT '堆叠上限',
   REMARK               VARCHAR(200) COMMENT '物品吐槽',
   IS_BINGDING          TINYINT COMMENT '是否绑定',
   ABOUT                VARCHAR(200) COMMENT '物品效果',
   STRONG               INT COMMENT '力量',
   INTE                 INT COMMENT '智力',
   PHY                  INT COMMENT '体力',
   SPI                  INT COMMENT '精神',
   ATT                  INT COMMENT '物理攻击',
   MAT                  INT COMMENT '魔法攻击',
   DEF                  INT COMMENT '物理防御',
   MEF                  INT COMMENT '魔法防御',
   MAX_HP               INT COMMENT 'maxHp',
   MAX_MP               INT COMMENT 'maxMp',
   MOVE_SPEED           TINYINT COMMENT '移动速度',
   HIT                  TINYINT COMMENT '命中率',
   PRIMARY KEY (ID)
);

ALTER TABLE TBL_BASE_ITEM COMMENT '物品基础信息表';

/*==============================================================*/
/* Table: TBL_BASE_SKILL                                        */
/*==============================================================*/
CREATE TABLE TBL_BASE_SKILL
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT 'ID',
   NAME                 VARCHAR(50) NOT NULL COMMENT '技能名称',
   LEVEL                TINYINT COMMENT '等级限制',
   PROFESSION           TINYINT COMMENT '职业限制',
   PRIMARY KEY (ID)
);

ALTER TABLE TBL_BASE_SKILL COMMENT '技能基本信息';

/*==============================================================*/
/* Table: TBL_ROLE                                              */
/*==============================================================*/
CREATE TABLE TBL_ROLE
(
   ID                   BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
   USER_ID              BIGINT NOT NULL COMMENT '用户ID',
   ROLE_NAME            VARCHAR(50) NOT NULL COMMENT '角色名称',
   CAREER_ID            TINYINT DEFAULT 0 COMMENT '职业类型ID',
   SEX                  TINYINT DEFAULT 0 COMMENT '性别',
   LEVEL                TINYINT DEFAULT 0 COMMENT '等级',
   EXP                  INT DEFAULT 0 COMMENT '经验',
   ROOKIE_ID            INT DEFAULT 0 COMMENT '新手任务ID',
   GUILD_ID             INT DEFAULT 0 COMMENT '帮会ID',
   GOLD                 INT UNSIGNED DEFAULT 0 COMMENT '金币',
   BIND_RMB             INT UNSIGNED DEFAULT 0 COMMENT '绑定RMB',
   SCENE_ID             INT DEFAULT 0 COMMENT '场景ID',
   HP                   INT DEFAULT 0 COMMENT '生命值HP',
   MP                   INT DEFAULT 0 COMMENT '魔法值MP',
   WISDOM               INT DEFAULT 0 COMMENT '智慧',
   DEXTERITY            INT DEFAULT 0 COMMENT '敏捷',
   STRENGTH             INT DEFAULT 0 COMMENT '力量',
   X                    INT DEFAULT 0 COMMENT '坐标X',
   Y                    INT DEFAULT 0 COMMENT '坐标Y',
   PHYSICAL_DEFENSE     INT DEFAULT 0 COMMENT '物理防御',
   PHYSICAL_DAMAGE      INT DEFAULT 0 COMMENT '物理伤害',
   MAGIC_DEFENSE        INT DEFAULT 0 COMMENT '法术防御',
   MAGIC_DAMAGE         INT DEFAULT 0 COMMENT '法术伤害',
   ONLINE_TIME          BIGINT DEFAULT 0 COMMENT '在线时间',
   CREATE_DATE          TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
   PK_VALUE             INT DEFAULT 0 COMMENT 'PK值',
   LAST_LOGOUT_DATE     DATETIME COMMENT '最后退出时间',
   GM_GRADE             TINYINT DEFAULT 0 COMMENT 'GM级别',
   PRIMARY KEY (ID)
);

ALTER TABLE TBL_ROLE COMMENT '角色信息';

/*==============================================================*/
/* Table: TBL_ROLE_FRIEND                                       */
/*==============================================================*/
CREATE TABLE TBL_ROLE_FRIEND
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT 'ID',
   ROLE_ID              BIGINT NOT NULL COMMENT '角色ID',
   FRIEND_IDS           VARCHAR(200) COMMENT '好友角色ID',
   FRIEND_TYPE          TINYINT COMMENT '好友类型,有白名单，黑名单，陌生人，公会好友等',
   PRIMARY KEY (ID)
);

ALTER TABLE TBL_ROLE_FRIEND COMMENT '好友列表信息';

/*==============================================================*/
/* Table: TBL_ROLE_SHORTCUT                                     */
/*==============================================================*/
CREATE TABLE TBL_ROLE_SHORTCUT
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT 'ID',
   ROLE_ID              BIGINT NOT NULL COMMENT '角色ID',
   SKILL_ID             INT NOT NULL COMMENT '技能ID',
   PRIMARY KEY (ID)
);

ALTER TABLE TBL_ROLE_SHORTCUT COMMENT '角色快捷键';

/*==============================================================*/
/* Table: TBL_USER                                              */
/*==============================================================*/
CREATE TABLE TBL_USER
(
   ID                   BIGINT NOT NULL AUTO_INCREMENT COMMENT '用户ID',
   USER_NAME            VARCHAR(50) NOT NULL COMMENT '用户名',
   PASSWORD             VARCHAR(32) NOT NULL COMMENT '密码',
   EMAIL                VARCHAR(50) COMMENT '邮箱',
   ACCOUNT_BALANCE      INT UNSIGNED DEFAULT 0 COMMENT '帐户余额',
   CREATE_DATE          TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
   LOGIN_COUNT          INT DEFAULT 0 COMMENT '登录次数',
   FLAG                 TINYINT DEFAULT 0 COMMENT '帐号状态0正常',
   LOGIN_IP             VARCHAR(50) COMMENT '用户IP',
   PRIMARY KEY (ID)
);

ALTER TABLE TBL_USER COMMENT '用户信息';

