# cop-service

Image : redhat-openjdk18-openshift:1.3

Template: oc new-app --template=openjdk18-web-basic-s2i

Case 1:
oc export template openjdk18-web-basic-s2i -n openshift > openjdk-basic-template.yml

oc process -f openjdk-basic-template.yml -p APPLICATION_NAME=cop-clock -p SOURCE_REPOSITORY_URL=https://github.com/sunnyf21/cop-service.git | oc apply -f-

Case 2: Template + Paramfile
oc process -f openjdk-basic-template.yml --param-file cop-param.txt | oc apply -f- -n cop-service

Case 3: Image+ Source : Route needs to be created manually

oc new-app redhat-openjdk18-openshift:1.3~https://github.com/sunnyf21/cop-service.git
