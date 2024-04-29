ALTER TABLE test_model
    ADD new_field1 VARCHAR(255) NULL;

ALTER TABLE test_model
    ADD new_field2 INT NULL;

ALTER TABLE test_model
    MODIFY new_field2 INT NOT NULL;