ALTER TABLE ta_kardex ADD COLUMN ciclo numeric(10,0);

ALTER TABLE ta_sistema ADD COLUMN idmateriales character varying(10);

ALTER TABLE ta_sistema ADD COLUMN snenviofactura character varying(1);

ALTER TABLE ta_articulo ADD COLUMN codigocontable character varying(10);