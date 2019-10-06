#Funções customizaas de validação de campo
import re

def email_validator(emai):
    valid = re.search("^[A-Z0-9._%+-]{1,64}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$",email)
    return bool(valid)

def telefone_validator(telefone):
    valid = re.search("^(\([0-9][0-9]\))?[0-9]{5,5}-?[0-9]{4,4}$",telefone)
    return bool(valid)
