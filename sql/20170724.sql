INSERT INTO ta_recursos(nmrecurso, titulo, accion, snsubmenu, nmrelacion, nmgrupo)
    VALUES (455, 'Consumos no facturados', 'consumo-no-facturado', 'N', 455, 4);

INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso)
    VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 455);
	
INSERT INTO ta_recursos(nmrecurso, titulo, accion, snsubmenu, nmrelacion, nmgrupo)
    VALUES (456, 'Estadísticas', 'estadisticas', 'N', 456, 4);

INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso)
    VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 456);
	
INSERT INTO ta_recursos(nmrecurso, titulo, accion, snsubmenu, nmrelacion, nmgrupo)
    VALUES (457, 'Consolidado por concepto', 'consolidado-concepto', 'N', 457, 4);

INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso)
    VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 457);
	
INSERT INTO ta_recursos(nmrecurso, titulo, accion, snsubmenu, nmrelacion, nmgrupo)
    VALUES (458, 'Recaudo detallado por pagos', 'detalle-recaudo', 'N', 458, 4);

INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso)
    VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 458);