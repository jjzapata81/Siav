INSERT INTO ta_recursos(nmrecurso, titulo, accion, snsubmenu, nmrelacion, nmgrupo)
    VALUES (455, 'Consumos no facturados', 'consumo-no-facturado', 'N', 455, 4);

INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso)
    VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 455);