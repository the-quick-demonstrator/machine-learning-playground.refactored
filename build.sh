rm -rf target
rm dependency-reduced-pom.xml
ls

echo Fetching any potential remote changes...
git fetch --all
git pull --all

if ! [[ -f "./mvnw" ]]; then
  echo "The file, ``./mvnw``, does not exist."
  echo "Running ``mvn -N wrapper:wrapper`` to generate ``./mvnw``..."
  mvn -N wrapper:wrapper
  echo "Successfully created file ``./mvnw``"
fi

./mvnw package -Dmaven.test.skip=true
