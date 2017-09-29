INSERT INTO ta_recursos(nmrecurso, titulo, accion, snsubmenu, nmrelacion, nmgrupo)
    VALUES (375, 'Facturación', 'factura', 'N', 375, 3);
    
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso)
    VALUES ((SELECT MAX(r.nmrecursoperfil) FROM ta_recurso_perfil r)+1, 1, 375);
    
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso)
    VALUES ((SELECT MAX(r.nmrecursoperfil) FROM ta_recurso_perfil r)+1, 2, 375);
