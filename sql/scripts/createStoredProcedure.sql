
CREATE OR REPLACE PROCEDURE public."addArtwork"(
	IN v_title character varying,
	IN v_year integer,
	IN v_type character varying,
	IN v_price double precision,
	IN v_a_name character varying,
	IN v_g_name character varying)
LANGUAGE 'plpgsql'
AS $BODY$
BEGIN
INSERT INTO a_group(g_name)
SELECT v_g_name
WHERE NOT EXISTS (SELECT g_name FROM a_group WHERE g_name = v_g_name);

INSERT INTO artwork(title, year, type, price, a_name) VALUES (v_title, v_year, v_type, v_price, v_a_name);

INSERT INTO classify(title, g_name) VALUES (v_title, v_g_name);

END
$BODY$;

