/*
 Navicat Premium Data Transfer

 Source Server         : JuaraCoding
 Source Server Type    : PostgreSQL
 Source Server Version : 120022 (120022)
 Source Host           : localhost:5433
 Source Catalog        : lacak_db
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 120022 (120022)
 File Encoding         : 65001

 Date: 29/01/2025 16:14:52
*/


-- ----------------------------
-- Sequence structure for locations_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."locations_id_seq";
CREATE SEQUENCE "public"."locations_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Table structure for locations
-- ----------------------------
DROP TABLE IF EXISTS "public"."locations";
CREATE TABLE "public"."locations" (
  "dem" int4,
  "elevation" int4,
  "id" int4 NOT NULL GENERATED BY DEFAULT AS IDENTITY (
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1
),
  "lat" float8,
  "lon" float8,
  "population" int8,
  "admin1" varchar(5000) COLLATE "pg_catalog"."default",
  "admin2" varchar(5000) COLLATE "pg_catalog"."default",
  "admin3" varchar(5000) COLLATE "pg_catalog"."default",
  "admin4" varchar(5000) COLLATE "pg_catalog"."default",
  "alt_name" varchar(5000) COLLATE "pg_catalog"."default",
  "ascii" varchar(5000) COLLATE "pg_catalog"."default",
  "cc2" varchar(5000) COLLATE "pg_catalog"."default",
  "country" varchar(5000) COLLATE "pg_catalog"."default",
  "feat_class" varchar(5000) COLLATE "pg_catalog"."default",
  "feat_code" varchar(5000) COLLATE "pg_catalog"."default",
  "modified_at" varchar(5000) COLLATE "pg_catalog"."default",
  "name" varchar(5000) COLLATE "pg_catalog"."default",
  "tz" varchar(5000) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of locations
-- ----------------------------

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."locations_id_seq"
OWNED BY "public"."locations"."id";
SELECT setval('"public"."locations_id_seq"', 1, false);

-- ----------------------------
-- Auto increment value for locations
-- ----------------------------
SELECT setval('"public"."locations_id_seq"', 1, false);

-- ----------------------------
-- Primary Key structure for table locations
-- ----------------------------
ALTER TABLE "public"."locations" ADD CONSTRAINT "locations_pkey" PRIMARY KEY ("id");
