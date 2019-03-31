import matplotlib.pyplot as plt  
import random, numpy as np
import pygame
from mpl_toolkits import mplot3d
import math
from scipy.special import factorial

def graph(equation, x_range):
    solutions = []
    for i in range(len(equation)):
        if equation[i] == 'x':
            if equation[i-1].isdigit() and i != 0:
                equation = equation[:i] + '*' + equation[i:]
    equation = equation.replace('^', '**')
    x = np.array(x_range)
    while '!' in equation:
        ind = equation.index('!')
        fcstr = '!'
        while (equation[ind-1].isalpha() or equation[ind-1].isdigit() or equation[ind-1] == '(' or equation[ind-1] == ')') and ind-1 >= 0:
            fcstr = equation[ind-1]+fcstr
            ind -= 1
        fc = fcstr[:-1]
        equation = equation.replace(fcstr, 'factorial('+fc+')')
    equation = equation.replace('x', '(x)')
    equation = equation.replace('((x))', '(x)')
    print(equation)
    y = eval(equation)
    print("###     SOLUTIONS    ###")
    print("########################")
    for i in range(len(y)):
        if y[i] == 0:
            sol = round(x[i], 3)
            sol = str(sol)
            if sol in solutions:
                continue
            solutions += [sol]
            print(sol)
            continue
        if (y[i-1] < 0 and y[i] > 0) or (y[i-1] > 0 and y[i] < 0):
            if x[i-1] < x[i]:
                nx = np.array(np.arange(x[i-1], x[i], 0.00001))
            else:
                nx = np.array(np.arange(x[i], x[i-1], 0.00001))
        else:
            continue
        n_equation = equation.replace('x', 'nx')
        ny = eval(n_equation)
        for j in range(len(ny)):
            if round(ny[j], 3) == 0:
                sol = round(nx[j], 3)
                radical = False
                newsol = sol
                deg = 1
                while not radical:
                    newsol *= sol
                    deg += 1
                    if deg > 6:
                        radical = True
                    if round(newsol, 2) == int(round(newsol, 2)):
                        sol = str(round(newsol, 2))+"^(1/"+str(deg)+")"
                        radical = True
                sol = str(sol)
                if sol in solutions:
                    continue
                solutions += [sol]
                print(sol)
    screen.fill([255, 255, 255])
    screen.blit(font.render("Solutions:", 1, [0, 0, 0]), [30, 300])
    mult = 1
    for i in solutions:
        screen.blit(font.render(i, 1, [0, 0, 0]), [30, 300+(30*mult)])
        mult += 1
    pygame.display.flip()
    plt.plot(x, y)  
    plt.show()

def init2d():
    go = True
    e = ''
    screen.fill([255, 255, 255])
    screen.blit(font.render("Enter the equation: ", 1, [0, 0, 0]), [30, 30])
    pygame.display.flip()
    while go:
        for event in pygame.event.get():
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_RETURN:
                    go = False
                elif event.key == pygame.K_BACKSPACE:
                    e = e[:-1]
                else:
                    e += event.unicode
                screen.fill([255, 255, 255])
                screen.blit(font.render("Enter the equation: ", 1, [0, 0, 0]), [30, 30])
                screen.blit(font.render(e, 1, [0, 0, 0]), [30, 100])
                pygame.display.flip()
                
    screen.fill([255, 255, 255])
    pygame.display.flip()
    go = True
    llim = ''
    screen.blit(font.render("Please specify a lower limit: ", 1, [0, 0, 0]), [30, 30])
    pygame.display.flip()
    while go:
        for event in pygame.event.get():
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_RETURN:
                    go = False
                elif event.key == pygame.K_BACKSPACE:
                    llim = llim[:-1]
                else:
                    llim += event.unicode
                screen.fill([255, 255, 255])
                screen.blit(font.render("Please specify a lower limit: ", 1, [0, 0, 0]), [30, 30])
                screen.blit(font.render(llim, 1, [0, 0, 0]), [30, 100])
                pygame.display.flip()

    screen.fill([255, 255, 255])
    pygame.display.flip()
    go = True
    ulim = ''
    screen.blit(font.render("Please specify a upper limit: ", 1, [0, 0, 0]), [30, 30])
    pygame.display.flip()
    while go:
        for event in pygame.event.get():
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_RETURN:
                    go = False
                elif event.key == pygame.K_BACKSPACE:
                    ulim = ulim[:-1]
                else:
                    ulim += event.unicode
                screen.fill([255, 255, 255])
                screen.blit(font.render("Please specify an upper limit: ", 1, [0, 0, 0]), [30, 30])
                screen.blit(font.render(ulim, 1, [0, 0, 0]), [30, 100])
                pygame.display.flip()

    llim = int(llim)
    ulim = int(ulim)
    graph(e, np.arange(llim, ulim, 1))
    
def init3d():
    go = True
    e = ''
    screen.fill([255, 255, 255])
    screen.blit(font.render("Enter the equation: ", 1, [0, 0, 0]), [30, 30])
    pygame.display.flip()
    while go:
        for event in pygame.event.get():
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_RETURN:
                    go = False
                elif event.key == pygame.K_BACKSPACE:
                    e = e[:-1]
                else:
                    e += event.unicode
                screen.fill([255, 255, 255])
                screen.blit(font.render("Enter the equation: ", 1, [0, 0, 0]), [30, 30])
                screen.blit(font.render(e, 1, [0, 0, 0]), [30, 100])
                pygame.display.flip()

    func = e
    fig = plt.figure()
    ax = plt.axes(projection='3d')

    for i in range(len(func)):
        if func[i] == 'x' or func[i] == 'y' or func[i] == 'z':
            if (func[i-1].isdigit() or func[i-1].isalpha()) and i != 0:
                func = func[:i] + '*' + func[i:]

    func = func.replace('^', "**")

    while '!' in func:
        ind = func.index('!')
        fcstr = '!'
        while (func[ind-1].isalpha() or func[ind-1].isdigit() or func[ind-1] == '(' or func[ind-1] == ')') and ind-1 >= 0:
            fcstr = func[ind-1]+fcstr
            ind -= 1
        fc = fcstr[:-1]
        func = func.replace(fcstr, 'factorial('+fc+')')
    func = func.replace('x', '(x)')
    func = func.replace('((x))', '(x)')

    print(func)

    r = 10
    step = 0.1

    if 'z' not in func:
        x = np.arange(-r, r, step)
        y = np.arange(-r, r, step)
        z = eval(func)
        for i in x:
            arr = np.full(int(r*2/step), i)
            ax.plot3D(arr, y, z, 'gray')
    if 'y' not in func:
        z = np.arange(-r, r, step)
        x = np.arange(-r, r, step)
        y = eval(func)
        for i in z:
            arr = np.full(int(r*2/step), i)
            ax.plot3D(x, y, arr, 'gray')
    if 'x' not in func:
        y = np.arange(-r, r, step)
        z = np.arange(-r, r, step)
        x = eval(func)
        for i in y:
            arr = np.full(int(r*2/step), i)
            ax.plot3D(x, arr, z, 'gray')
        
    #ax.plot3D(x, y, z, 'gray')
    plt.show()

def phys():
    screen.fill([255, 255, 255])
    screen.blit(font.render("Enter the initial vertical speed of the object (feet/second).", 1, [0, 0, 0]), [10, 10])
    pygame.display.flip()
    go = True
    v = ''
    while go:
        for event in pygame.event.get():
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_RETURN:
                    go = False
                if event.key == pygame.K_BACKSPACE:
                    v = v[:-1]
                else:
                    v += event.unicode
                screen.fill([255, 255, 255])
                screen.blit(font.render("Enter the initial vertical speed of the object (feet/second).", 1, [0, 0, 0]), [10, 10])
                screen.blit(font.render(v, 1, [0, 0, 0]), [30, 100])
                pygame.display.flip()
    v = int(v)
    
    screen.fill([255, 255, 255])
    screen.blit(font.render("Enter the initial height of the object (feet).", 1, [0, 0, 0]), [10, 10])
    pygame.display.flip()
    go = True
    h = ''
    while go:
        for event in pygame.event.get():
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_RETURN:
                    go = False
                if event.key == pygame.K_BACKSPACE:
                    h = h[:-1]
                else:
                    h += event.unicode
                screen.fill([255, 255, 255])
                screen.blit(font.render("Enter the initial height of the object (feet).", 1, [0, 0, 0]), [10, 10])
                screen.blit(font.render(h, 1, [0, 0, 0]), [30, 100])
                pygame.display.flip()
    h = int(h)

    screen.fill([255, 255, 255])
    screen.blit(font.render("Enter the time in seconds to find the height of the object.", 1, [0, 0, 0]), [10, 10])
    pygame.display.flip()
    go = True
    t = ''
    while go:
        for event in pygame.event.get():
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_RETURN:
                    go = False
                if event.key == pygame.K_BACKSPACE:
                    t = t[:-1]
                else:
                    t += event.unicode
                screen.fill([255, 255, 255])
                screen.blit(font.render("Enter the time in seconds to find the height of the object.", 1, [0, 0, 0]), [10, 10])
                screen.blit(font.render(t, 1, [0, 0, 0]), [30, 100])
                pygame.display.flip()
    t = int(t)  

    screen.fill([255, 255, 255])
    screen.blit(font.render("Given an initial vertical speed of "+str(v)+" feet per second", 1, [0, 0, 0]), [10, 10])
    screen.blit(font.render("Given an initial height of "+str(h)+" feet", 1, [0, 0, 0]), [10, 50])
    finalheight = (-16)*t*t+(v*t)+h
    if finalheight <= 0:
        finalheight = 0
    screen.blit(font.render("This object will have a height of "+str(finalheight)+" feet after "+str(t)+" seconds.", 1, [0 ,0, 0]), [10, 90])
    pygame.display.flip()
              

pygame.init()
screen = pygame.display.set_mode([750, 750])
screen.fill([255, 255, 255])
pygame.display.flip()
font = pygame.font.Font(None, 35)


choicemade = False
screen.blit(font.render("Press 2 for 2d graph and equation solver.", 1, [0, 0, 0]), [10, 10])
screen.blit(font.render("Press 3 for 3d interactive graph.", 1, [0, 0, 0]), [10, 50])
screen.blit(font.render("Press p to use the projectile motion function.", 1, [0, 0, 0]), [10, 90])
pygame.display.flip()
while not choicemade:
    for event in pygame.event.get():
        if event.type == pygame.KEYDOWN:
            if event.key == pygame.K_2:
                init2d()
                choicemade = True
            if event.key == pygame.K_3:
                init3d()
                choicemade = True
            if event.key == pygame.K_p:
                phys()
            

