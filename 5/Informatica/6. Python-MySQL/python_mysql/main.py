from mysql import connector

def main():
    db = connector.connect(
        host="localhost",
        user="root",
        password="",
        database="arte",
    )

    cursor = db.cursor()
    cursor.execute("SHOW TABLES;")

    print(cursor.fetchall())
