<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
      xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/course">
<html>
<head>
    <title>Course</title>
</head>
<body>

<h2>Course details</h2>

<ul>
    <li><b>ID:</b> <xsl:value-of select="id"/></li>
    <li><b>Title:</b> <xsl:value-of select="title"/></li>
    <li><b>Professor:</b> <xsl:value-of select="professor"/></li>
</ul>

<p>
    <a href="/api/courses">Back to courses</a>
</p>

</body>
</html>
</xsl:template>

</xsl:stylesheet>
