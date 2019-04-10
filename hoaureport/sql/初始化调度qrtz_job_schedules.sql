--调度
INSERT INTO qrtz_job_schedules  ( ID ,  TRIGGER_GROUP ,  TRIGGER_NAME ,  JOB_GROUP ,  JOB_NAME ,  DESCRIPTION ,
  TRIGGER_TYPE ,  TRIGGER_EXPRESSION ,  JOB_CLASS ,  JOB_DATA )
  VALUES ('1', 'G1', 'J1', 'HARJOB', 'OrgSyncJob', '部门同步', 1, '*/2 * * * *', 'com.hoau.hoaureport.module.job.server.job.OrgSyncJob', '');


INSERT INTO  qrtz_job_plannings  ( INSTANCE_ID ,  SCOPE_TYPE ,  SCOPE_NAME ,  ACCESS_RULE ) VALUES ('har-job', 1, 'HARJOB', 1);
