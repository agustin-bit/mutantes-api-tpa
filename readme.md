# Desafio MercadoLibre
Agustín Peruzzi y Sandra Chilayo

## Repo
https://github.com/agustin-bit/mutantes-api-tpa

## Deploy
https://mutantes-api-tpa.herokuapp.com

## Tecnologías
- Java 11
- Spring Boot
- Redis
- Heroku

## Ejemplo de uso

### Ver si ADN es mutante

- POST https://mutantes-api-tpa.herokuapp.com/mutant

Body JSON:
```json
{    
    "dna" : ["AAAA","TACG","GTAA","TTCA"]
}
```

Estados HTTP validos:
- 403: HUMANO
- 200: MUTANTE

***

### Obtener estadísticas

- POST https://mutantes-api-tpa.herokuapp.com/stats

***

## Diagramas

Adjuntamos diagrama de secuencia y clase en PPT adjunto
