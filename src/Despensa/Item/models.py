from django.db import models
from Estoque.models import *
from Usuario.models import *

# Create your models here.

class TipoProduto(models.Model):
    nome = models.CharField(max_length=100)

    def __str__(self):
        return self.nome

class Item(models.Model):
    nome = models.CharField(max_length=100)
    preco = models.DecimalField(max_digits=6,decimal_places=2)

    quantidade = models.IntegerField()
    numero_consumidores = models.IntegerField()
    gasto_previsto = models.IntegerField()

    validade = models.DateField(blank=True)
    data_hora_insercao = models.DateTimeField(auto_now=True)

    estoque = models.ForeignKey(Estoque, on_delete=models.CASCADE)
    usuario_comprou = models.ForeignKey(Usuario, null=True, on_delete=models.SET_NULL)
    tipo_produto = models.ForeignKey(TipoProduto, on_delete=models.CASCADE)

    def __str__(self):
        return self.nome

