ALTER TABLE ta_sistema ADD COLUMN idmatricula character varying(10);
UPDATE ta_sistema SET idmatricula = '222222' WHERE id = 1;
INSERT INTO ta_tarifas(cdconcepto, tipo, descripcion, estrato0, estrato1, estrato2,estrato3, estrato4, estrato5, estrato6)
    VALUES ('222222', 2, 'INICIAL MATRÍCULA', 0, 0, 0, 0, 0, 0, 0);