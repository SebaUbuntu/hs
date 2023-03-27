import turtle as t
from turtle import Turtle, Screen

turtle = Turtle()
screen = Screen()

turtle.shape("turtle")
#turtle.speed(0)

def forward():
    turtle.forward(10)

def backward():
    turtle.forward(-10)

def left():
    turtle.left(10)

def right():
    turtle.right(10)

t.listen()

t.onkey(forward, "Up")
t.onkey(backward, "Down")
t.onkey(left, "Left")
t.onkey(right, "Right")

screen.exitonclick()
