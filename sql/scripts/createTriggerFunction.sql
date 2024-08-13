
CREATE OR REPLACE FUNCTION public."addLike_ArtistTriggerFunction"()
    RETURNS trigger
    LANGUAGE 'plpgsql'
AS $BODY$
BEGIN

INSERT INTO like_artist(cust_id,  a_name)
SELECT NEW.cust_id, a1.a_name
FROM artist as a1
INNER JOIN artwork as a2 ON a2.a_name = a1.a_name
INNER JOIN classify as c1 ON c1.title = a2.title
INNER JOIN like_group as g1 ON g1.g_name = c1.g_name;
RETURN NULL;
END
$BODY$;


CREATE TRIGGER "addLike_GroupTrigger"
    AFTER INSERT
    ON public.like_group
    FOR EACH ROW
    EXECUTE FUNCTION public."addLike_ArtistTriggerFunction"();
