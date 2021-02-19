import math

def isValid(grid, row, column, number):
    # check the row
    for c in range(len(grid)):
        if grid[row][c] == number:
            return False;

    # check the column
    for r in range(len(grid)):
        if grid[r][column] == number:
            return False

    # check the 3x3 subgrid
    sub_size = int(math.sqrt(len(grid))) 
    sub_row_start = row - (row % sub_size)
    sub_column_start = column - (column % sub_size)

    i = sub_row_start
    while i < (sub_row_start + sub_size):
        j = sub_column_start
        while j < (sub_column_start + sub_size):
            if grid[i][j] == number:
                return False
            j += 1
        i += 1

    return True

def solveSudoku(grid):
    to_solve = False
    for i in range(len(grid)):
        for j in range(len(grid)):
            if grid[i][j] == 0:
                row = i
                column = j
                to_solve = True
                break
        if to_solve:
            break

    if not to_solve:
        return True

    for number in range(1, len(grid) + 1):
        if isValid(grid, row, column, number):
            grid[row][column] = number
            if solveSudoku(grid):
                return True
            else:
                grid[row][column] = 0

    return False

def printSolution(grid):
    for row in range(len(grid)):
        if row % 3 == 0:
            print("-------------------------------")
        print("|", end =" ")

        for column in range(len(grid)):
            print(grid[row][column], end =" ")
            if column % 3 == 2:
                print("| ", end ="")
            else:
                print(" ", end ="")

        print()
    print("-------------------------------")

#grid = [
#    [ 3, 0, 6, 5, 0, 8, 4, 0, 0 ],
#    [ 5, 2, 0, 0, 0, 0, 0, 0, 0 ],
#    [ 0, 8, 7, 0, 0, 0, 0, 3, 1 ],
#    [ 0, 0, 3, 0, 1, 0, 0, 8, 0 ],
#    [ 9, 0, 0, 8, 6, 3, 0, 0, 5 ],
#    [ 0, 5, 0, 0, 9, 0, 6, 0, 0 ],
#    [ 1, 3, 0, 0, 0, 0, 2, 5, 0 ],
#    [ 0, 0, 0, 0, 0, 0, 0, 7, 4 ],
#    [ 0, 0, 5, 2, 0, 6, 3, 0, 0 ]
#]

grid = list()
size = int(input("Enter grid size: "))
for i in range(size):
    row = list()
    row_string = input(f"Enter space between elements of row {i}: ")
    for x in row_string.strip().split():
        row.append(int(x))
    grid.append(row)

if solveSudoku(grid):
    printSolution(grid)
else:
    print("No solution.")