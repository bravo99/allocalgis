ALTER TABLE parcelas ADD COLUMN ninterno numeric(7,0);
ALTER TABLE parcelas ADD COLUMN tipo character varying(1);
ALTER TABLE parcelas ADD COLUMN numerodup character varying(1);
ALTER TABLE parcelas ADD COLUMN numsymbol numeric(1,0);


INSERT INTO columns (id, name, id_domain, id_table, "Length", "Precision", "Scale", "Type") VALUES (nextval('seq_columns'), 'ninterno', null, 100, null, 7, 0, 2);
insert into dictionary (id_vocablo,locale, traduccion) values (nextval('seq_dictionary'),'es_ES','ninterno');
insert into attributes (id, id_alias, id_layer, id_column, position, editable) values (nextval('seq_attributes'),currval('seq_dictionary'),101,currval('seq_columns'),23,TRUE);
INSERT INTO columns (id, name, id_domain, id_table, "Length", "Precision", "Scale", "Type") VALUES (nextval('seq_columns'), 'tipo', null, 100, 1, null, null, 3);
insert into dictionary (id_vocablo,locale, traduccion) values (nextval('seq_dictionary'),'es_ES','tipo');
insert into attributes (id, id_alias, id_layer, id_column, position, editable) values (nextval('seq_attributes'),currval('seq_dictionary'),101,currval('seq_columns'),24,TRUE);
INSERT INTO columns (id, name, id_domain, id_table, "Length", "Precision", "Scale", "Type") VALUES (nextval('seq_columns'), 'numerodup', null, 100, 1, null, null, 3);
insert into dictionary (id_vocablo,locale, traduccion) values (nextval('seq_dictionary'),'es_ES','numerodup');
insert into attributes (id, id_alias, id_layer, id_column, position, editable) values (nextval('seq_attributes'),currval('seq_dictionary'),101,currval('seq_columns'),25,TRUE);
INSERT INTO columns (id, name, id_domain, id_table, "Length", "Precision", "Scale", "Type") VALUES (nextval('seq_columns'), 'numsymbol', null, 100, null, 1, 0, 2);
insert into dictionary (id_vocablo,locale, traduccion) values (nextval('seq_dictionary'),'es_ES','numsymbol');
insert into attributes (id, id_alias, id_layer, id_column, position, editable) values (nextval('seq_attributes'),currval('seq_dictionary'),101,currval('seq_columns'),26,TRUE);

UPDATE queries SET selectquery='SELECT Parcelas.id, Parcelas.referencia_catastral, Parcelas.id_municipio, Parcelas.primer_numero, Parcelas.primera_letra, Parcelas.segundo_numero, Parcelas.segunda_letra, Parcelas.kilometro, Parcelas.bloque, Parcelas.direccion_no_estructurada, Parcelas.codigo_postal, Parcelas.fecha_alta, Parcelas.fecha_baja, transform(Parcelas."GEOMETRY", ?T) as "GEOMETRY", Parcelas.codigodelegacionmeh, Parcelas.nombreentidadmenor, Parcelas.id_distrito, Parcelas.codigozonaconcentracion, Parcelas.codigopoligono, Parcelas.codigoparcela, Parcelas.nombreparaje, Parcelas.id_via, Parcelas.area, Parcelas.length, Parcelas.anno_expediente, Parcelas.referencia_expediente, Parcelas.codigo_entidad_colaboradora, Parcelas.tipo_movimiento, Parcelas.codigo_municipiodgc, Parcelas.bice, Parcelas.codigo_provinciaine, Parcelas.codigo_municipio_localizaciondgc, Parcelas.codigo_municipio_localizacionine, Parcelas.codigo_municipio_origendgc, Parcelas.codigo_paraje, Parcelas.superficie_finca, Parcelas.superficie_construida_total, Parcelas.superficie_const_sobrerasante, Parcelas.superficie_const_bajorasante, Parcelas.superficie_cubierta, Parcelas.anio_aprobacion, Parcelas.codigo_calculo_valor, Parcelas.poligono_catastral_valoracion, Parcelas.modalidad_reparto, Parcelas.indicador_infraedificabilidad, Parcelas.movimiento_baja, Parcelas.coordenada_x, Parcelas.coordenada_y, Parcelas.modificado, Municipios.NombreOficial, Parcelas.ninterno, Parcelas.tipo, Parcelas.numerodup, Parcelas.numsymbol FROM Municipios INNER JOIN Parcelas ON (Municipios.ID=Parcelas.ID_Municipio) WHERE Municipios.ID in (?M) AND Fecha_baja IS NULL', updatequery='UPDATE parcelas SET "GEOMETRY"=transform(GeometryFromText(text(?1),?S), ?T),id=?2,referencia_catastral=?3,id_municipio=?M,id_distrito=?5,codigoparcela=?6,codigopoligono=?7,id_via=?8,primer_numero=?9,primera_letra=?10,segundo_numero=?11,segunda_letra=?12,kilometro=?13,bloque=?14,direccion_no_estructurada=?15,codigo_postal=?16,codigodelegacionmeh=?17,length=perimeter(GeometryFromText(text(?1),?S)),Area=area2d(GeometryFromText(text(?1),?S)), fecha_alta=?20,fecha_baja=?21,modificado=?22,ninterno=?23, tipo=?24, numerodup=?25, numsymbol=?26 WHERE ID=?2', insertquery='INSERT INTO parcelas ("GEOMETRY",id,referencia_catastral,id_municipio,id_distrito,codigoparcela,codigopoligono,id_via,primer_numero,primera_letra,segundo_numero,segunda_letra,kilometro,bloque,direccion_no_estructurada,codigo_postal,codigodelegacionmeh,length,area,fecha_alta,fecha_baja,modificado,ninterno, tipo, numerodup, numsymbol) VALUES(transform(GeometryFromText(text(?1),?S), ?T),?PK,?3,?M,?5,?6,?7,?8,?9,?10,?11,?12,?13,?14,?15,?16,?17,perimeter(GeometryFromText(text(?1),?S)),area2d(GeometryFromText(text(?1),?S)),?20,?21,?22,?23,?24,?25,?26)' WHERE id_layer = 101;