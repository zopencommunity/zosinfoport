node('linux') {
  stage ('Poll') {
    checkout([
      $class: 'GitSCM', branches: [[name: '*/main']], extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/zopencommunity/zoOSInfoport.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [
      string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/zopencommunity/zoOSInfoport.git'),
      string(name: 'PORT_DESCRIPTION', value: 'zOSInfo is a lightweight C++ utility for retrieving system-level information from z/OS Unix System Services (USS).'),
      string(name: 'BUILD_LINE', value: 'DEV')
    ]
  }
}
