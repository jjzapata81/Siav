DROP TABLE ta_login;
DROP TABLE ta_roles;

CREATE TABLE ta_formato_recaudo
(
  idformato character varying(2) NOT NULL,
  fecha numeric(2,0) NOT NULL,
  valor numeric(2,0) NOT NULL,
  referencia numeric(2,0) NOT NULL,
  formato_fecha character varying(18) NOT NULL,
  separador character varying(1) NOT NULL,
  separador_aux character varying(1),
  posicion_aux numeric(1,0),
  posicion_inicial_fra numeric(2,0) NOT NULL,
  posicion_final_fra numeric(2,0) NOT NULL,
  posicion_inicial_ciclo numeric(2,0) NOT NULL,
  posicion_final_ciclo numeric(2,0) NOT NULL,
  CONSTRAINT pk_ta_formato_recaudo PRIMARY KEY (idformato)
);

CREATE TABLE ta_comprobante_pago
(
  nmcomprobante numeric(6,0) NOT NULL,
  nminstalacion numeric(10,0),
  cedula character varying(50),
  valor numeric(10,0),
  usuario character varying(10) NOT NULL,
  fecha timestamp without time zone,
  nmcredito numeric(10,0),
  CONSTRAINT pk_ta_comprobante_pago PRIMARY KEY (nmcomprobante)
);

INSERT INTO ta_formato_recaudo(idformato, fecha, valor, referencia, formato_fecha, separador, separador_aux, posicion_aux, posicion_inicial_fra, posicion_final_fra, posicion_inicial_ciclo, posicion_final_ciclo)
    VALUES ('1', 0, 5, 8, 'dd-MM-yyyy', ',', '-', 0, 4, 10, 10, 13);
