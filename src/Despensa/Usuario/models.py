from django.db import models
from .validators import email_validator, telefone_validator

# Create your models here.

class Familia(models.Model):
    nome = models.CharField(max_length=100)

    def __str__(self):
        return self.nome
    

class Usuario(models.Model):
    nome = models.CharField(max_length=100)
    email = models.CharField(max_length=200,validators=[email_validator,])
    telefone = models.CharField(max_length=14,validators=[telefone_validator,])

    idade = models.IntegerField()
    consumo_diario = models.IntegerField()

    data_hora_insercao = models.DateTimeField(auto_now=True)

    familia = models.ForeignKey(Familia, on_delete=models.CASCADE)

    def __str__(self):
        return self.nome
