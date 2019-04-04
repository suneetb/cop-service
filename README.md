# cop-service

Using Image : redhat-openjdk18-openshift:1.3

Case 1:
oc export template openjdk18-web-basic-s2i -n openshift > openjdk-basic-template.yml


oc process -f openjdk-basic-template.yml -p APPLICATION_NAME=cop-clock -p SOURCE_REPOSITORY_URL=https://github.com/sunnyf21/cop-service.git | oc apply -f-

Case 2:
oc new-app --template=openjdk18-web-basic-s2

Case 3:
oc process -f openjdk-basic-template.yml --param-file cop-param.txt | oc apply -f- -n cop-service
