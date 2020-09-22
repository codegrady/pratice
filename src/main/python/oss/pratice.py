#!/usr/bin/python3
import math

def quadraric(a,b,c):
    m=math.sqrt(b*b-4*a*c)
    x1=(-b+m)/2*a
    x2=(-b-m)/2*a
    if quadraric(2,3,1):
        print(x1)


def main():
    quadraric(2,3,4)
if __name__ == "__main__":
    main()
