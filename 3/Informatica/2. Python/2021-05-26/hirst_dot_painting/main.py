import turtle as t
from turtle import Turtle, Screen
import colorgram
from random import choice

t.colormode(255)

turtle = Turtle()
screen = Screen()

turtle.shape("turtle")
turtle.speed(0)

turtle.penup()

colors = list(colorgram.extract("image.jpg", 30))

for i in range(10):
    for j in range(10):
        turtle.dot(20, choice(colors).rgb)
        turtle.forward(50)
    turtle.right(90)
    turtle.forward(50)
    turtle.right(90)
    turtle.forward(500)
    turtle.right(180)

screen.exitonclick()
