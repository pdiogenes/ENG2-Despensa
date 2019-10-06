# Generated by Django 2.2.6 on 2019-10-06 19:31

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    initial = True

    dependencies = [
        ('Estoque', '0001_initial'),
        ('Usuario', '0001_initial'),
    ]

    operations = [
        migrations.CreateModel(
            name='TipoProduto',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('nome', models.CharField(max_length=100)),
            ],
        ),
        migrations.CreateModel(
            name='Item',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('nome', models.CharField(max_length=100)),
                ('preco', models.DecimalField(decimal_places=2, max_digits=6)),
                ('quantidade', models.IntegerField()),
                ('numero_consumidores', models.IntegerField()),
                ('gasto_previsto', models.IntegerField()),
                ('validade', models.DateField(blank=True)),
                ('data_hora_insercao', models.DateTimeField(auto_now=True)),
                ('estoque', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='Estoque.Estoque')),
                ('tipo_produto', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='Item.TipoProduto')),
                ('usuario_comprou', models.ForeignKey(null=True, on_delete=django.db.models.deletion.SET_NULL, to='Usuario.Usuario')),
            ],
        ),
    ]
