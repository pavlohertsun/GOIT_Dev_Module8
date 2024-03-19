SELECT 'youngest' AS type, name, birthday
FROM worker
WHERE birthday = (
    SELECT max(birthday)
    FROM worker
)
UNION ALL
SELECT 'oldest' AS type, name, birthday
FROM worker
WHERE birthday = (
    SELECT min(birthday)
    FROM worker
);