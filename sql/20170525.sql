INSERT INTO ta_recursos(nmrecurso, titulo, snsubmenu, nmrelacion, nmgrupo) VALUES (342, 'Comprobantes', 'S', 342, 3);
INSERT INTO ta_recursos(nmrecurso, titulo, accion, snsubmenu, nmrelacion, nmgrupo) VALUES (346, 'Crédito', 'credito-abono', 'N', 342, 3);
INSERT INTO ta_recursos(nmrecurso, titulo, accion, snsubmenu, nmrelacion, nmgrupo) VALUES (347, 'Matrícula', 'matricula-abono', 'N', 342, 3);
UPDATE ta_recursos SET nmrelacion = 342 WHERE nmrecurso = 345;
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES (40, 1, 342);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES (41, 1, 346);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES (42, 1, 347);