from django.db import models
from Usuario.models import Familia

# Create your models here.

class Estoque(models.Model):
    capacidade = models.IntegerField()
    qtde_armazenada = models.IntegerField()
    familia = models.ForeignKey(Familia, on_delete=models.CASCADE)

    def __str__(self):
        return "Estoque " + str(self.id)
