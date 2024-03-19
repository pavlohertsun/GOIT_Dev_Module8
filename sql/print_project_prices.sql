SELECT p.id AS project_id,
       SUM(w.salary) * EXTRACT(MONTH FROM AGE(p.finish_date, p.start_date)) AS project_cost
FROM project p
JOIN project_worker pw ON p.id = pw.project_id
JOIN worker w ON pw.worker_id = w.id
GROUP BY p.id
ORDER BY project_cost DESC;
