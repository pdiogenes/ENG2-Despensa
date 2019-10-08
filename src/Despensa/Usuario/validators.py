#Funções customizaas de validação de campo
from django.core.exceptions import ValidationError
import re

def email_validator(emai):
    valid = re.search("^[A-Z0-9._%+-]{1,64}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$",email)
    if not valid:
        raise ValidationError(
            ("Email invalido"),
            params={'value': value},
        )

def telefone_validator(telefone):
    valid = re.search("^(\([0-9][0-9]\))?[0-9]{5,5}-?[0-9]{4,4}$",telefone)
    if not valid:
        raise ValidationError(
            ("Telefone invalido"),
            params={'value': value},
        )
