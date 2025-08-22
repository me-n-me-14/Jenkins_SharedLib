def call(String Project, String ImageTag, String dockerhubuserParam) {
  withCredentials([usernamePassword(credentialsId: 'docker', passwordVariable: 'dockerhubpass', usernameVariable: 'dockerhubuserCred')]) {
    // Use the username from credentials, not the method parameter (to avoid variable shadowing)
    sh """
      echo ${dockerhubpass} | docker login -u ${dockerhubuserCred} --password-stdin
      docker push ${dockerhubuserParam}/${Project}:${ImageTag}
    """
  }
}
