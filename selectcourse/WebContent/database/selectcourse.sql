/*
Navicat Oracle Data Transfer
Oracle Client Version : 11.2.0.1.0

Source Server         : oracle_11
Source Server Version : 110200
Source Host           : localhost:1522
Source Schema         : SYSTEM

Target Server Type    : ORACLE
Target Server Version : 110200
File Encoding         : 65001

selectcourse 所需要的表

Date: 2018-12-03 14:25:46
*/

-- ----------------------------
-- Table structure for SYS_ADMIN
-- ----------------------------
DROP TABLE "SYSTEM"."SYS_ADMIN";
CREATE TABLE "SYSTEM"."SYS_ADMIN" (
"ADMID" NUMBER NOT NULL ,
"ADMNAME" VARCHAR2(12 BYTE) NULL ,
"ADMPWD" VARCHAR2(20 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

INSERT INTO "SYSTEM"."SYS_ADMIN" VALUES ('1', 'admin', '123456');

ALTER TABLE "SYSTEM"."SYS_ADMIN" ADD PRIMARY KEY ("ADMID");



-- ----------------------------
-- Table structure for SYS_COURSES
-- ----------------------------
DROP TABLE "SYSTEM"."SYS_COURSES";
CREATE TABLE "SYSTEM"."SYS_COURSES" (
"COUID" VARCHAR2(10 BYTE) NOT NULL ,
"COUNAME" VARCHAR2(20 BYTE) NULL ,
"TEACHER" VARCHAR2(12 BYTE) NULL ,
"CREDIT" NUMBER(3,1) NULL ,
"COUEXP" VARCHAR2(50 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

INSERT INTO "SYSTEM"."SYS_COURSES" VALUES ('001', 'Java基础', '张三丰', '3', 'java基础，Java入门的好书籍');
INSERT INTO "SYSTEM"."SYS_COURSES" VALUES ('002', 'Oracle基础教程', '刘斌', '2', 'Oracle基础教程，包括实战项目');
INSERT INTO "SYSTEM"."SYS_COURSES" VALUES ('003', '数据结构', '彰化', '2', '数据结构');
INSERT INTO "SYSTEM"."SYS_COURSES" VALUES ('004', '大学语文', '张无忌', '3', '大学语文');
INSERT INTO "SYSTEM"."SYS_COURSES" VALUES ('005', '高等数学', '夏江华', '3', '高等数学');
INSERT INTO "SYSTEM"."SYS_COURSES" VALUES ('006', '大学英语', 'Tom', '2', '大学英语');
INSERT INTO "SYSTEM"."SYS_COURSES" VALUES ('007', '计算机基础', '张翠山', '2', '计算机基础');
INSERT INTO "SYSTEM"."SYS_COURSES" VALUES ('008', '化学', '殷素素', '1', '化学');
INSERT INTO "SYSTEM"."SYS_COURSES" VALUES ('009', '体育', '高圆圆', '1', '体育');
INSERT INTO "SYSTEM"."SYS_COURSES" VALUES ('010', '游泳', '王祖贤', '2', '游泳');
INSERT INTO "SYSTEM"."SYS_COURSES" VALUES ('011', 'C++程序设计', '段誉', '3', 'C++程序设计');
INSERT INTO "SYSTEM"."SYS_COURSES" VALUES ('012', 'C语言', '张无忌', '1', 'C语言');
INSERT INTO "SYSTEM"."SYS_COURSES" VALUES ('014', '大学英语', 'Rose', '3', '大学英语');
INSERT INTO "SYSTEM"."SYS_COURSES" VALUES ('013', '线性代数', '吴凡', '3', '线性代数');
INSERT INTO "SYSTEM"."SYS_COURSES" VALUES ('015', '陶瓷概论', '詹嘉', '2', '陶瓷概论');
INSERT INTO "SYSTEM"."SYS_COURSES" VALUES ('016', 'Linux实践', '程斌', '1', 'Linux实践');

ALTER TABLE "SYSTEM"."SYS_COURSES" ADD PRIMARY KEY ("COUID");


-- ----------------------------
-- Table structure for SYS_STUDENTS
-- ----------------------------
DROP TABLE "SYSTEM"."SYS_STUDENTS";
CREATE TABLE "SYSTEM"."SYS_STUDENTS" (
"STUID" VARCHAR2(10 BYTE) NOT NULL ,
"STUNAME" VARCHAR2(12 BYTE) NULL ,
"STUPWD" VARCHAR2(20 BYTE) NULL ,
"STUSEX" VARCHAR2(4 BYTE) NULL ,
"STUINSTITUTE" VARCHAR2(20 BYTE) NULL ,
"STULOGINNAME" VARCHAR2(20 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

ALTER TABLE "SYSTEM"."SYS_STUDENTS" ADD PRIMARY KEY ("STUID");

-- ----------------------------
-- Table structure for SYS_SHOWLOG
-- ----------------------------
DROP TABLE "SYSTEM"."SYS_SHOWLOG";
CREATE TABLE "SYSTEM"."SYS_SHOWLOG" (
"LOGID" NUMBER NOT NULL ,
"STUID" VARCHAR2(10 BYTE) NULL ,
"USERNAME" VARCHAR2(12 BYTE) NULL ,
"DOING" VARCHAR2(20 BYTE) NULL ,
"MSG" VARCHAR2(100 BYTE) NULL ,
"LOGDATE" VARCHAR2(30 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

ALTER TABLE "SYSTEM"."SYS_SHOWLOG" ADD PRIMARY KEY ("LOGID");

-- ----------------------------
-- Table structure for SYS_STUCOU
-- ----------------------------
DROP TABLE "SYSTEM"."SYS_STUCOU";
CREATE TABLE "SYSTEM"."SYS_STUCOU" (
"STUID" VARCHAR2(10 BYTE) NOT NULL ,
"COUID" VARCHAR2(10 BYTE) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

ALTER TABLE "SYSTEM"."SYS_STUCOU" ADD PRIMARY KEY ("STUID", "COUID");
