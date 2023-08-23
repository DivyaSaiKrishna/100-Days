import random
from hangman_words import word_list
from hangman_banner import hangmanbanner

choosen_word = random.choice(word_list)
print(hangmanbanner)
print("Only 6 wrong guess are consider")
arr=[]
life=0
le_sz = len(choosen_word)
for _ in range(len(choosen_word)):
    arr += "_"

end_game = False

display = ""

while not end_game:
    letter = input("Guess the word with character:").lower()
    
    for position in range(len(choosen_word)):
        
        if letter == choosen_word[position]:
            if letter in arr:
                display = "already guessed"
            else:
                display = "good"
            arr[position] = letter
    
    
            
    if letter not in choosen_word:
        display = "this is not in the word"
        life+=1
        if life == 6:
            end_game = True
            display ="hangman died"
    
    if "_" not in arr:
        display = "you won :)"
        end_game = True
    import os
    from hangman_pics import HANGMANPICS
    os.system('clear')
    print(HANGMANPICS[life])
    print(display)

    print(arr)