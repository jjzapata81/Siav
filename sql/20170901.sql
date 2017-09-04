INSERT INTO ta_datosmaestros(id, grupo, cdcodigo, dsvalor)
    VALUES ((SELECT MAX(d.id) FROM ta_datosmaestros d)+1,'SALIDA_DESTINO', 1, 'INSTALACIÓN');

INSERT INTO ta_datosmaestros(id, grupo, cdcodigo, dsvalor)
    VALUES ((SELECT MAX(d.id) FROM ta_datosmaestros d)+1,'SALIDA_DESTINO', 2, 'ACUEDUCTO');

