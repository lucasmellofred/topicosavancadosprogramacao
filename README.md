# topicosavancadosprogramacao

## Comandos no terminal para rodar esse projeto:
```
export JAVA_HOME=/usr/lib/jvm/java-1.17.0-openjdk-amd64

mvn clean install -DskipTests

sudo dapr run -f .

dapr publish --publish-app-id tapr-2023-equipe2-aluno-java --pubsub servicebus-pubsub --topic topico-equipe-2-aluno --data '{"id": "123","nome": "Lucas"}'
```
