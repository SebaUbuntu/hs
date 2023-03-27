import turtle as t
from turtle import Turtle, Screen

screen = Screen()
screen.setup(width=500, height=400)

turtles = [Turtle(shape="turtle") for _ in range(5)]

factor = screen.window_height()

for i, turtle in enumerate(turtles):
    turtle.penup()
    turtle.goto((-(screen.window_width() / 2), 0))

screen.exitonclick()
