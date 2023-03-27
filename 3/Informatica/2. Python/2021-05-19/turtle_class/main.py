from turtle import Turtle, Screen
import random

turtle = Turtle()
screen = Screen()

turtle.shape("turtle")
turtle.speed(0)

"""
def draw_gon(sides):
    for _ in range(sides):
        turtle.forward(100)
        turtle.right(360/sides)

for i in range(3,111):
    draw_gon(i)
"""

def random_walk():
    turtle.right(random.randint(0, 4) * 90)
    turtle.forward(10)
    random_walk()

random_walk()

screen.exitonclick()
