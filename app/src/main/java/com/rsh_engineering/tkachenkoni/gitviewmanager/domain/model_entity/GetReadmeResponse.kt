package com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 *
 * Created by Nikolay Tkachenko on 22, October, 2020
 *
 */
//https://raw.githubusercontent.com/moby/moby/master/README.md

@JsonClass(generateAdapter = true)
data class GetReadmeResponse(
    @Json(name = "name")
    val name: String,
    @Json(name = "path")
    val path: String,
    @Json(name = "sha")
    val sha: String,
    @Json(name = "size")
    val size: String,
    @Json(name = "url")
    val url: String,
    @Json(name = "html_url")
    val html_url: String,
    @Json(name = "git_url")
    val git_url: String,
    @Json(name = "download_url")
    val download_url: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "content")
    val content: String,
    @Json(name = "encoding")
    val encoding: String,

//{
//    "name": "README.md",
//    "path": "README.md",
//    "sha": "534fd97db3e926329e631c2d7b26dd7ef5d1bc75",
//    "size": 3395,
//    "url": "https://api.github.com/repos/moby/moby/contents/README.md?ref=master",
//    "html_url": "https://github.com/moby/moby/blob/master/README.md",
//    "git_url": "https://api.github.com/repos/moby/moby/git/blobs/534fd97db3e926329e631c2d7b26dd7ef5d1bc75",
//    "download_url": "https://raw.githubusercontent.com/moby/moby/master/README.md",
//    "type": "file",
//    "content": "VGhlIE1vYnkgUHJvamVjdAo9PT09PT09PT09PT09PT09CgohW01vYnkgUHJv\namVjdCBsb2dvXShkb2NzL3N0YXRpY19maWxlcy9tb2J5LXByb2plY3QtbG9n\nby5wbmcgIlRoZSBNb2J5IFByb2plY3QiKQoKTW9ieSBpcyBhbiBvcGVuLXNv\ndXJjZSBwcm9qZWN0IGNyZWF0ZWQgYnkgRG9ja2VyIHRvIGVuYWJsZSBhbmQg\nYWNjZWxlcmF0ZSBzb2Z0d2FyZSBjb250YWluZXJpemF0aW9uLgoKSXQgcHJv\ndmlkZXMgYSAiTGVnbyBzZXQiIG9mIHRvb2xraXQgY29tcG9uZW50cywgdGhl\nIGZyYW1ld29yayBmb3IgYXNzZW1ibGluZyB0aGVtIGludG8gY3VzdG9tIGNv\nbnRhaW5lci1iYXNlZCBzeXN0ZW1zLCBhbmQgYSBwbGFjZSBmb3IgYWxsIGNv\nbnRhaW5lciBlbnRodXNpYXN0cyBhbmQgcHJvZmVzc2lvbmFscyB0byBleHBl\ncmltZW50IGFuZCBleGNoYW5nZSBpZGVhcy4KQ29tcG9uZW50cyBpbmNsdWRl\nIGNvbnRhaW5lciBidWlsZCB0b29scywgYSBjb250YWluZXIgcmVnaXN0cnks\nIG9yY2hlc3RyYXRpb24gdG9vbHMsIGEgcnVudGltZSBhbmQgbW9yZSwgYW5k\nIHRoZXNlIGNhbiBiZSB1c2VkIGFzIGJ1aWxkaW5nIGJsb2NrcyBpbiBjb25q\ndW5jdGlvbiB3aXRoIG90aGVyIHRvb2xzIGFuZCBwcm9qZWN0cy4KCiMjIFBy\naW5jaXBsZXMKCk1vYnkgaXMgYW4gb3BlbiBwcm9qZWN0IGd1aWRlZCBieSBz\ndHJvbmcgcHJpbmNpcGxlcywgYWltaW5nIHRvIGJlIG1vZHVsYXIsIGZsZXhp\nYmxlIGFuZCB3aXRob3V0IHRvbyBzdHJvbmcgYW4gb3BpbmlvbiBvbiB1c2Vy\nIGV4cGVyaWVuY2UuCkl0IGlzIG9wZW4gdG8gdGhlIGNvbW11bml0eSB0byBo\nZWxwIHNldCBpdHMgZGlyZWN0aW9uLgoKLSBNb2R1bGFyOiB0aGUgcHJvamVj\ndCBpbmNsdWRlcyBsb3RzIG9mIGNvbXBvbmVudHMgdGhhdCBoYXZlIHdlbGwt\nZGVmaW5lZCBmdW5jdGlvbnMgYW5kIEFQSXMgdGhhdCB3b3JrIHRvZ2V0aGVy\nLgotIEJhdHRlcmllcyBpbmNsdWRlZCBidXQgc3dhcHBhYmxlOiBNb2J5IGlu\nY2x1ZGVzIGVub3VnaCBjb21wb25lbnRzIHRvIGJ1aWxkIGZ1bGx5IGZlYXR1\ncmVkIGNvbnRhaW5lciBzeXN0ZW0sIGJ1dCBpdHMgbW9kdWxhciBhcmNoaXRl\nY3R1cmUgZW5zdXJlcyB0aGF0IG1vc3Qgb2YgdGhlIGNvbXBvbmVudHMgY2Fu\nIGJlIHN3YXBwZWQgYnkgZGlmZmVyZW50IGltcGxlbWVudGF0aW9ucy4KLSBV\nc2FibGUgc2VjdXJpdHk6IE1vYnkgcHJvdmlkZXMgc2VjdXJlIGRlZmF1bHRz\nIHdpdGhvdXQgY29tcHJvbWlzaW5nIHVzYWJpbGl0eS4KLSBEZXZlbG9wZXIg\nZm9jdXNlZDogVGhlIEFQSXMgYXJlIGludGVuZGVkIHRvIGJlIGZ1bmN0aW9u\nYWwgYW5kIHVzZWZ1bCB0byBidWlsZCBwb3dlcmZ1bCB0b29scy4KVGhleSBh\ncmUgbm90IG5lY2Vzc2FyaWx5IGludGVuZGVkIGFzIGVuZCB1c2VyIHRvb2xz\nIGJ1dCBhcyBjb21wb25lbnRzIGFpbWVkIGF0IGRldmVsb3BlcnMuCkRvY3Vt\nZW50YXRpb24gYW5kIFVYIGlzIGFpbWVkIGF0IGRldmVsb3BlcnMgbm90IGVu\nZCB1c2Vycy4KCiMjIEF1ZGllbmNlCgpUaGUgTW9ieSBQcm9qZWN0IGlzIGlu\ndGVuZGVkIGZvciBlbmdpbmVlcnMsIGludGVncmF0b3JzIGFuZCBlbnRodXNp\nYXN0cyBsb29raW5nIHRvIG1vZGlmeSwgaGFjaywgZml4LCBleHBlcmltZW50\nLCBpbnZlbnQgYW5kIGJ1aWxkIHN5c3RlbXMgYmFzZWQgb24gY29udGFpbmVy\ncy4KSXQgaXMgbm90IGZvciBwZW9wbGUgbG9va2luZyBmb3IgYSBjb21tZXJj\naWFsbHkgc3VwcG9ydGVkIHN5c3RlbSwgYnV0IGZvciBwZW9wbGUgd2hvIHdh\nbnQgdG8gd29yayBhbmQgbGVhcm4gd2l0aCBvcGVuIHNvdXJjZSBjb2RlLgoK\nIyMgUmVsYXRpb25zaGlwIHdpdGggRG9ja2VyCgpUaGUgY29tcG9uZW50cyBh\nbmQgdG9vbHMgaW4gdGhlIE1vYnkgUHJvamVjdCBhcmUgaW5pdGlhbGx5IHRo\nZSBvcGVuIHNvdXJjZSBjb21wb25lbnRzIHRoYXQgRG9ja2VyIGFuZCB0aGUg\nY29tbXVuaXR5IGhhdmUgYnVpbHQgZm9yIHRoZSBEb2NrZXIgUHJvamVjdC4K\nTmV3IHByb2plY3RzIGNhbiBiZSBhZGRlZCBpZiB0aGV5IGZpdCB3aXRoIHRo\nZSBjb21tdW5pdHkgZ29hbHMuIERvY2tlciBpcyBjb21taXR0ZWQgdG8gdXNp\nbmcgTW9ieSBhcyB0aGUgdXBzdHJlYW0gZm9yIHRoZSBEb2NrZXIgUHJvZHVj\ndC4KSG93ZXZlciwgb3RoZXIgcHJvamVjdHMgYXJlIGFsc28gZW5jb3VyYWdl\nZCB0byB1c2UgTW9ieSBhcyBhbiB1cHN0cmVhbSwgYW5kIHRvIHJldXNlIHRo\nZSBjb21wb25lbnRzIGluIGRpdmVyc2Ugd2F5cywgYW5kIGFsbCB0aGVzZSB1\nc2VzIHdpbGwgYmUgdHJlYXRlZCBpbiB0aGUgc2FtZSB3YXkuIEV4dGVybmFs\nIG1haW50YWluZXJzIGFuZCBjb250cmlidXRvcnMgYXJlIHdlbGNvbWVkLgoK\nVGhlIE1vYnkgcHJvamVjdCBpcyBub3QgaW50ZW5kZWQgYXMgYSBsb2NhdGlv\nbiBmb3Igc3VwcG9ydCBvciBmZWF0dXJlIHJlcXVlc3RzIGZvciBEb2NrZXIg\ncHJvZHVjdHMsIGJ1dCBhcyBhIHBsYWNlIGZvciBjb250cmlidXRvcnMgdG8g\nd29yayBvbiBvcGVuIHNvdXJjZSBjb2RlLCBmaXggYnVncywgYW5kIG1ha2Ug\ndGhlIGNvZGUgbW9yZSB1c2VmdWwuClRoZSByZWxlYXNlcyBhcmUgc3VwcG9y\ndGVkIGJ5IHRoZSBtYWludGFpbmVycywgY29tbXVuaXR5IGFuZCB1c2Vycywg\nb24gYSBiZXN0IGVmZm9ydHMgYmFzaXMgb25seSwgYW5kIGFyZSBub3QgaW50\nZW5kZWQgZm9yIGN1c3RvbWVycyB3aG8gd2FudCBlbnRlcnByaXNlIG9yIGNv\nbW1lcmNpYWwgc3VwcG9ydDsgRG9ja2VyIEVFIGlzIHRoZSBhcHByb3ByaWF0\nZSBwcm9kdWN0IGZvciB0aGVzZSB1c2UgY2FzZXMuCgotLS0tLQoKTGVnYWwK\nPT09PT0KCipCcm91Z2h0IHRvIHlvdSBjb3VydGVzeSBvZiBvdXIgbGVnYWwg\nY291bnNlbC4gRm9yIG1vcmUgY29udGV4dCwKcGxlYXNlIHNlZSB0aGUgW05P\nVElDRV0oaHR0cHM6Ly9naXRodWIuY29tL21vYnkvbW9ieS9ibG9iL21hc3Rl\nci9OT1RJQ0UpIGRvY3VtZW50IGluIHRoaXMgcmVwby4qCgpVc2UgYW5kIHRy\nYW5zZmVyIG9mIE1vYnkgbWF5IGJlIHN1YmplY3QgdG8gY2VydGFpbiByZXN0\ncmljdGlvbnMgYnkgdGhlClVuaXRlZCBTdGF0ZXMgYW5kIG90aGVyIGdvdmVy\nbm1lbnRzLgoKSXQgaXMgeW91ciByZXNwb25zaWJpbGl0eSB0byBlbnN1cmUg\ndGhhdCB5b3VyIHVzZSBhbmQvb3IgdHJhbnNmZXIgZG9lcyBub3QKdmlvbGF0\nZSBhcHBsaWNhYmxlIGxhd3MuCgpGb3IgbW9yZSBpbmZvcm1hdGlvbiwgcGxl\nYXNlIHNlZSBodHRwczovL3d3dy5iaXMuZG9jLmdvdgoKTGljZW5zaW5nCj09\nPT09PT09PQpNb2J5IGlzIGxpY2Vuc2VkIHVuZGVyIHRoZSBBcGFjaGUgTGlj\nZW5zZSwgVmVyc2lvbiAyLjAuIFNlZQpbTElDRU5TRV0oaHR0cHM6Ly9naXRo\ndWIuY29tL21vYnkvbW9ieS9ibG9iL21hc3Rlci9MSUNFTlNFKSBmb3IgdGhl\nIGZ1bGwKbGljZW5zZSB0ZXh0Lgo=\n",
//    "encoding": "base64",
//    "_links": {
//        "self": "https://api.github.com/repos/moby/moby/contents/README.md?ref=master",
//        "git": "https://api.github.com/repos/moby/moby/git/blobs/534fd97db3e926329e631c2d7b26dd7ef5d1bc75",
//        "html": "https://github.com/moby/moby/blob/master/README.md"
//    }
//}
)