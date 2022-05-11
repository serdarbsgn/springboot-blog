# springboot-blog
stuff that  i've learned through a springboot camp. 
a blog api with all the unit and integration tests i've learned run on it.
tests have a coverage of %100(that is a good thing i think :) )
model classes have written on kotlin, and dto's.
controller, repository, services and exceptions are in java.
only hard part for me was figuring out the correct one to one relationship with blog and user. user has a blog, when upon deletion cascades and deletes the blog and other user related content from database but if blog is deleted, it shouldn't affect the user and their comments about other posts. using cascade.remove on blog side solved the issue?     
