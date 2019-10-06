from django.db import models
from Item.models import Item

# Create your models here.

class ListaItemEvento(models.Model):
    item = models.ForeignKey(Item, on_delete=models.CASCADE)
    evento = models.ForeignKey('Evento', on_delete=models.CASCADE)
    qtde = models.IntegerField()

    def __str__(self):
        return self.item+" "+self.evento

class Evento(models.Model):
    nome = models.CharField(max_length=100)
    data = models.DateField()
    lista_produtos = models.ManyToManyField(Item,
            through=ListaItemEvento,
            through_fields=('evento', 'item'),
    )

    def __str__(self):
        return self.nome
