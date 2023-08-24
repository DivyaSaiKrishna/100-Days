from caesarcipher_banner import art
print(art)

from caesarcipher_alph import alph

def encription(input_string, shift_number):
    encrripted_text = ""
    for letter in input_string:
        position = alph.index(letter)
        new_position = position + shift_number
        if new_position > 26:
            new_position = new_position-26
        encrripted_text += alph[new_position]
    print(f"Encripted text : {encrripted_text}")
    
def decription(input_string, shift_number):
    decrripted_text = ""
    for letter in input_string:
        position = alph.index(letter)
        old_position = position - shift_number
        decrripted_text += alph[old_position]
    print(f"decripted {decrripted_text}")

close = True
while close:
    decision = input("encript or decript : ")
    inp_str = input("enter the string : ")
    shf_num = int(input("enter the shift number : "))
    if decision == "encript":
        encription(inp_str,shf_num)
    elif decision == "decript":
        decription(inp_str,shf_num)
    check = input("do you want continue (yes/no) : ").lower()
    if check == "no":
        close = False
    

    
    

        
        