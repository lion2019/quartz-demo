CREATE TABLE IF NOT EXISTS task_info (
  id          INTEGER PRIMARY KEY AUTO_INCREMENT,
  name        VARCHAR(64) NOT NULL,
  cron   VARCHAR(40) NOT NULL);