INSERT INTO ta_perfiles(nmperfil, dsperfil) VALUES (4, 'Gerencia');
INSERT INTO ta_perfiles(nmperfil, dsperfil) VALUES (5, 'Contable');

INSERT INTO ta_recursos(nmrecurso, titulo, accion, snsubmenu, nmrelacion, nmgrupo)
    VALUES (107, 'Estructura acueducto', 'estructura-acueducto', 'N', 107, 1);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso)
    VALUES ((SELECT MAX(r.nmrecursoperfil) FROM ta_recurso_perfil r)+1, 1, 107);