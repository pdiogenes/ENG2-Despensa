# Generated by Django 2.2.6 on 2019-10-06 19:31

import Usuario.validators
from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Familia',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('nome', models.CharField(max_length=100)),
            ],
        ),
        migrations.CreateModel(
            name='Usuario',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('nome', models.CharField(max_length=100)),
                ('email', models.CharField(max_length=200, validators=[Usuario.validators.email_validator])),
                ('telefone', models.CharField(max_length=14, validators=[Usuario.validators.telefone_validator])),
                ('idade', models.IntegerField()),
                ('consumo_diario', models.IntegerField()),
                ('data_hora_insercao', models.DateTimeField(auto_now=True)),
                ('familia', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='Usuario.Familia')),
            ],
        ),
    ]
