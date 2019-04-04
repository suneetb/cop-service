# cop-service

oc export template openjdk18-web-basic-s2i -n openshift > openjdk-basic-template.yml


oc process -f openjdk-basic-template.yml -p APPLICATION_NAME=clock -p SOURCE_REPOSITORY_URL=https://github.com/saswatsarangi0914/cop-service.git -p CONTEXT_DIR='hellowworld' | oc apply -f

oc new-app --template=openjdk18-web-basic-s2

 oc process -f openjdk-basic-template.yml --param-file cop-param.txt | oc apply -f- -n cop-service
