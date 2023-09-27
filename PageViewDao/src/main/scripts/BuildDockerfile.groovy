String template = new File("${project.basedir}/src/main/docker/DockerfileTemplate".toString()).getText();

def dockerFileText = new groovy.text.SimpleTemplateEngine().createTemplate(template).make([fileName: project.build.finalName])

new File("${project.basedir}/target/dockerfile/".toString()).mkdirs()

File dockerFile = new File("${project.basedir}/target/dockerfile/Dockerfile".toString())

dockerFile.withWriter('UTF-8'){
    writer -> writer.write(dockerFileText)
}