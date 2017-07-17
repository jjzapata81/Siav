INSERT INTO ta_recursos(nmrecurso, titulo, accion, snsubmenu, nmrelacion, nmgrupo)
    VALUES (325, 'Nota crédito', 'nota-credito', 'N', 325, 3);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso)
    VALUES ((SELECT MAX(r.nmrecursoperfil) FROM ta_recurso_perfil r)+1, 1, 325);