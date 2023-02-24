Project: Create Your Own Home Page

Objectives

Familiarity with using simple HTML markup
Exposure to using several Eclipse features including creating a project, creating a file, importing a file, structured editing of HTML, opening files with multiple tools, and exporting a project
Exposure to what is meant by developing "life-long learning" skills
The Problem

The lingua franca of the web is HTML: Hyper Text Markup Language. Your job is to learn enough about well-formed HTML to create a personal home page that includes the following elements: at least one heading, at least one paragraph, at least one image (e.g., a picture of yourself), at least one ordered or unordered list, at least one table (e.g., information about yourself that you are willing to share publicly), and at least one hyperlink to an external page.

Method

If you don't already know enough HTML to do this project, you're not alone! Fortunately, there are many decent HTML tutorials available on the web, e.g., Learn XHTML and W3Schools. We recommend that you start early and ask questions in class if you run into problems.

Create a new Eclipse project by using File > New > Project..., where you should navigate in the list of wizards to Web > Static Web Project. (Do not copy the ProjectTemplate Eclipse project created in lab, which is a template for your up-coming Java programming projects, not this one.) Name the new project HomePage and accept the default settings by cicking Finish. You may be asked whether you want Eclipse to open the Web perspective, if so, click Open Perspective. In the Project Explorer view right-click on the public folder of this project to get the contextual pop-up menu and create a new file in it by using New > HTML File (or New > Other...; navigate to Web > HTML File, click Next), then name the new file index.html. Click on Next and, from the choice of templates, to generate the initial contents of the new file, choose New XHTML File (1.0 strict); this allows the HTML editor to support you in creating well-formed HTML. Do not change the first five lines of the file, which contain the ?xml, !DOCTYPE, and meta elements that are already set up appropriately.
Use Eclipse's file import feature to get an image file into the same folder as index.html so you can meet the image-element requirement. First, get an image file (maybe on the web or from your own photo gallery, if you have one) onto the computer you're using to run Eclipse. Then right-click on the public folder to get the contextual pop-up menu, choose Import..., navigate in the wizard to General > File System, browse to find the image file you wish to use, and import it into the project.
Use Eclipse's HTML editor to edit the initial page to meet the problem requirements above. Several particular features will be your friends: in the HTML editor, check out the menu item Source > Format; in the Project Explorer, right-click on index.html to get the contextual pop-up menu and check out Open With so you can see what your page looks like in a web browser; and in the editor area, pay attention to the warnings Eclipse provides when your HTML is not XHTML-compliant.
Once your page is done and you believe you have satisfied all the project requirements, validate your page by uploading it at Markup Validation Service. Make sure you address all errors and warnings returned by the validator before submitting your project.
Select your Eclipse project HomePage in the Project Explorer (not just some of the files, but the whole project), create a zip archive of it, and submit the zip archive to the Carmen dropbox for this project, as described in Submitting a Project.
