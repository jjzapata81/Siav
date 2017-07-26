alter table ta_factura_maestro alter column feconsignacion type date;
alter table ta_log_pagos alter column fehasta type date;
alter table ta_ciclos alter column fecha type date;
INSERT INTO ta_recursos(nmrecurso, titulo, accion, snsubmenu, nmrelacion, nmgrupo)
    VALUES (459, 'Cartera detallada créditos', 'cartera', 'N', 459, 4);

INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso)
    VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 459);