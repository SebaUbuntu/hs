SELECT user()
SELECT current_user()
CREATE USER 'prova'@'localhost' IDENTIFIED BY '12345678';
SELECT * FROM Prova
GRANT SELECT ON Prova.* TO prova@localhost WITH GRANT OPTION
REVOKE SELECT ON Prova.* TO prova@localhost
